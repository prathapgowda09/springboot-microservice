package com.partha.ios.resource;

import com.partha.ios.resource.model.CatalogItem;
import com.partha.ios.resource.model.Movie;
import com.partha.ios.resource.model.Rating;
import com.partha.ios.resource.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private  WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        UserRating userRating = restTemplate.getForObject("http://movie-rating-service/ratings/user/" + userId, UserRating.class);

        return userRating.getUserRating().stream().map(rating -> {

            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

            return new CatalogItem(movie.getName(), "Good Movie", rating.getRating());
        }).collect(Collectors.toList());

            /*
            //another way to communicate with movie-info-service
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8200/movies/ " + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            */
    }
}
