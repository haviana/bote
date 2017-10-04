import com.github.pabloo99.xmlsoccer.api.dto.GetLiveScoreResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.api.dto.GetLeagueStandingsResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetTeamResultDto;
import com.github.pabloo99.xmlsoccer.model.enums.Leagues;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args)
    {
        XmlSoccerService xmlSoccerService = new XmlSoccerServiceImpl();
        xmlSoccerService.setApiKey("CXCEKXPEVVESCOQSUFHSPARCRQNEKYZWIHZNVVKGJDAAJXEXHW");

        // demo access
        xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballDataDemo.asmx");

        // full access
        //xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballData.asmx");

        GetTeamResultDto getTeamResultDto = xmlSoccerService.getTeam("Celtic");
        System.out.println(getTeamResultDto.toString());

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());


        List<GetLiveScoreResultDto> getLiveScoreResultDto = xmlSoccerService.getLiveScore().stream().collect(Collectors.toList());

        System.out.println(getLiveScoreResultDto.toString());

        List<GetLeagueStandingsResultDto> getLeagueStandingsResultDtoList = xmlSoccerService.getLeagueStandingsBySeason("Scottish Premier League", "1415").stream().collect(Collectors.toList());

        System.out.println(getLeagueStandingsResultDtoList.toString());

        /* to pass in a parameter the name of the league or the season,
        ** you can use specially prepared enumerated type,
        ** which can be found in the package pl.com.pabloo99.xmlsoccer.model.enums
        **
        ** example:
        ** List<GetLeagueStandingsResultDto> getLeagueStandingsResultDtoList = xmlSoccerService.
                                    getLeagueStandingsBySeason(Leagues.SCOTTISH_PREMIER_LEAGUE.getParam(),
                                                               Seasons.SEASON_2014_2015.getParam()).
                                    stream().
                                    collect(Collectors.toList());

        ** System.out.println(getLeagueStandingsResultDtoList.toString());
        */

    }
}