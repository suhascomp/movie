package com.movie.moviecatalogservice.resource;

import com.movie.moviecatalogservice.models.CatalogItem;
import com.movie.moviecatalogservice.models.MovieInfo;
import com.movie.moviecatalogservice.models.Rating;
import com.movie.moviecatalogservice.services.MovieInfoService;
import com.movie.moviecatalogservice.services.RatingInfoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @Autowired
    private RatingInfoService ratingInfoService;

    @Autowired
    private MovieInfoService movieInfoService;

    /*@Autowired
    private WebClient.Builder builder;*/

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalogByUserId(@PathVariable("userId") int userId) {
        List<MovieInfo> list = Arrays.asList(
                new MovieInfo(123, "Batman", "desc"),
                new MovieInfo(32, "Transformers" , "desc")
        );
        return list.stream().map(e -> {
            Rating rating = ratingInfoService.getRating(e);

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

    @GetMapping("movie-summery/{id}")
//    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public CatalogItem getSummery(@PathVariable("id") int id) {
        MovieInfo movieInfo = movieInfoService.getMovieInfo(id);
        Rating rating = ratingInfoService.getRating(movieInfo);
        CatalogItem catalogItem = new CatalogItem(movieInfo.getName(), movieInfo.getDesc(), rating.getRating());
        return catalogItem;
    }


    public CatalogItem getFallbackCatalog(@PathVariable("id") int id) {
        return new CatalogItem("No name", "No desc",0);
    }
}
