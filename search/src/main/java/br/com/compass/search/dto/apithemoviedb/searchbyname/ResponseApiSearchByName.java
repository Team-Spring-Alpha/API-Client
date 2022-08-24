package br.com.compass.search.dto.apithemoviedb.searchbyname;

import br.com.compass.search.dto.apithemoviedb.ResponseApiResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseApiSearchByName {
    private int page;
    private List<ResponseApiResult> results;
    @JsonProperty("total_results")
    private int totalResults;
    @JsonProperty("total_pages")
    private int totalPages;

}
