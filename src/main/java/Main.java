import com.github.pabloo99.xmlsoccer.api.dto.*;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.model.enums.Leagues;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;
import com.github.pabloo99.xmlsoccer.webservice.GetHistoricMatchesByLeagueAndSeason;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws ParseException {
        XmlSoccerService xmlSoccerService = new XmlSoccerServiceImpl();
        xmlSoccerService.setApiKey("CXCEKXPEVVESCOQSUFHSPARCRQNEKYZWIHZNVVKGJDAAJXEXHW");

        // demo access
        xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballDataDemo.asmx");

        // full access
        //xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballData.asmx");

        GetTeamResultDto getTeamResultDto = xmlSoccerService.getTeam("Celtic");
        System.out.println("Equipa: "+ getTeamResultDto.toString());


        List<GetLiveScoreResultDto> getLiveScoreResultDto = xmlSoccerService.getLiveScore().stream().collect(Collectors.toList());
        List<GetHistoricMatchesResultDto> getHistoricalMatchts = xmlSoccerService.getHistoricMatchesByLeagueAndSeason("Scottish Premier League","1718").stream().collect(Collectors.toList());

        for (GetHistoricMatchesResultDto historicalMatches: getHistoricalMatchts)
            System.out.println(historicalMatches);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date today = new Date();
//        String date=sdf.format(today);
      String date="2017-10-14T00:00:00+01:00";
        System.out.println(date);

        System.out.println(getLiveScoreResultDto.toString());

        List<GetFixturesResultDto> getNextMatchOddsByLeague = xmlSoccerService.getFixturesByLeagueAndSeason("Scottish Premier League","1718").stream().collect(Collectors.toList());
        for (GetFixturesResultDto result: getNextMatchOddsByLeague )
            System.out.println("Resultado:" +result);

        List<GetAllOddsResultDto> test = xmlSoccerService.getAllOddsByFixtureMatchId(380372).
                stream().
                collect(Collectors.toList());

        for (GetAllOddsResultDto tes:test
             ) {
            System.out.println("\nOdds:"+ tes);
        }
        List<GetLeagueStandingsResultDto> getLeagueStandingsResultDtoList = xmlSoccerService.getLeagueStandingsBySeason("Scottish Premier League", "1718").stream().collect(Collectors.toList());

        for (GetLeagueStandingsResultDto league:getLeagueStandingsResultDtoList)
            System.out.println(league);

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