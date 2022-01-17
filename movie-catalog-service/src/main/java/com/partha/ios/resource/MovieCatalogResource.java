package com.partha.ios.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.partha.ios.resource.model.CatalogItem;
import com.partha.ios.resource.model.Movie;
import com.partha.ios.resource.model.Rating;
import com.partha.ios.resource.model.UserRating;
import com.partha.ios.resource.service.MovieInfo;
import com.partha.ios.resource.service.UserRatingInfo;
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

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @GetMapping("/{userId}")
    //@HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        UserRating userRating = userRatingInfo.getUserRating(userId);

        return userRating.getUserRating().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());

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



//    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
//        return  Arrays.asList(new CatalogItem("No Movie","",0));
//    }



}
