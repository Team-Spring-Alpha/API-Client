package br.com.compass.search.utils;

import br.com.compass.search.dto.apiclient.response.ResponseApiClient;
import br.com.compass.search.dto.apiclient.response.ResponseJustWatch;
import br.com.compass.search.dto.apithemoviedb.ResponseApiResult;
import br.com.compass.search.dto.apithemoviedb.searchByDate.ResponseApiSearchByDate;
import br.com.compass.search.dto.apithemoviedb.searchbyname.ResponseApiSearchByName;
import br.com.compass.search.enums.GenresEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ModelMapperUtils {

    private final WebClientUtils webClientUtils;
    private final RentPrice rentPrice;

    public List<ResponseApiClient> responseSearchByNameToApiClient(ResponseApiSearchByName apiSearchByName) {
        List<ResponseApiClient> responseApiClientList = new ArrayList<>();

        for (int i = 0; i < apiSearchByName.getResults().size(); i++) {
            ResponseApiResult responseApiResult = apiSearchByName.getResults().get(i);
            ResponseApiClient responseApiClient = new ResponseApiClient();

            List<GenresEnum> genresEnumList = genresIdToGenresString(responseApiResult.getGenreIds());
            List<String> actors = webClientUtils.getActorsByMovieId(responseApiResult.getId());
            String yearRelease = responseApiResult.getReleaseDate().substring(0, 4);
            Double rentPrice = this.rentPrice.getRentPriceFromYear(yearRelease);
            ResponseJustWatch responseJustWatch = webClientUtils.getJustWatchDataFromMovieIdAndRentPrice(responseApiResult.getId(), rentPrice);

            responseApiClient.setTitle(responseApiResult.getTitle());
            responseApiClient.setGenrers(genresEnumList);
            responseApiClient.setReleaseYear(yearRelease);
            responseApiClient.setActors(actors);
            responseApiClient.setOverview(responseApiResult.getOverview());
            responseApiClient.setPoster(responseApiResult.getPosterPath());
            responseApiClient.setJustWatch(responseJustWatch);

            responseApiClientList.add(responseApiClient);
        }
        return responseApiClientList;
    }

    public List<GenresEnum> genresIdToGenresString(List<Long> genresIds) {
        List<GenresEnum> genresEnumList = new ArrayList<>();
        for (Long genresId : genresIds) {
            GenresEnum genresEnum = GenresEnum.valueOfId(genresId);
            genresEnumList.add(genresEnum);
        }
        return genresEnumList;
    }

    public List<ResponseApiClient> responseSearchByDateToApiClient(ResponseApiSearchByDate apiSearchByDate) {
        List<ResponseApiClient> responseApiClientList = new ArrayList<>();

        for (int i = 0; i < apiSearchByDate.getResults().size(); i++) {
            ResponseApiResult responseApiResult = apiSearchByDate.getResults().get(i);
            ResponseApiClient responseApiClient = new ResponseApiClient();

            List<GenresEnum> genresEnumList = genresIdToGenresString(responseApiResult.getGenreIds());
            List<String> actors = webClientUtils.getActorsByMovieId(responseApiResult.getId());
            String yearRelease = responseApiResult.getReleaseDate().substring(0, 4);
            Double rentPrice = this.rentPrice.getRentPriceFromYear(yearRelease);
            ResponseJustWatch responseJustWatch = webClientUtils.getJustWatchDataFromMovieIdAndRentPrice(responseApiResult.getId(), rentPrice);

            responseApiClient.setTitle(responseApiResult.getTitle());
            responseApiClient.setGenrers(genresEnumList);
            responseApiClient.setReleaseYear(yearRelease);
            responseApiClient.setActors(actors);
            responseApiClient.setOverview(responseApiResult.getOverview());
            responseApiClient.setPoster(responseApiResult.getPosterPath());
            responseApiClient.setJustWatch(responseJustWatch);

            responseApiClientList.add(responseApiClient);
        }
        return responseApiClientList;
    }



}
