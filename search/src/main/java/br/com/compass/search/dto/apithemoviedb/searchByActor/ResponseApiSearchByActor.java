package br.com.compass.search.dto.apithemoviedb.searchByActor;

import br.com.compass.search.dto.apithemoviedb.ResponseApiResultActor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseApiSearchByActor {
    private int page;
    private List<ResponseApiResultActor> results;
    @JsonProperty("total_results")
    private int totalResults;
    @JsonProperty("total_pages")
    private int totalPages;
}
