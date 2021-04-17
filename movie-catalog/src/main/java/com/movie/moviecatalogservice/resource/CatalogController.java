package com.movie.moviecatalogservice.resource;

import com.movie.moviecatalogservice.models.CatalogItem;
import com.movie.moviecatalogservice.models.MovieInfo;
import com.movie.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient.Builder builder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalogByUserId(@PathVariable("userId") int userId) {
        List<MovieInfo> list = Arrays.asList(
                new MovieInfo(123, "Batman"),
                new MovieInfo(32, "Transformers")
        );
        return list.stream().map(e -> {
            Rating rating =
            restTemplate.getForObject("http://movie-rating/rating/getById/" + e.getMovieId(),Rating.class);

//            builder.build()
//                    .get()
//                    .uri("http://localhost:8082/rating/getById/" + e.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Rating.class)
//                    .block();


            return new CatalogItem(e.getName(), "desc", rating.getRating());
        }).collect(Collectors.toList());

//        return Collections.singletonList(
//                new CatalogItem("Batman", "Batmen begins", 9 )
//        );
    }
}
