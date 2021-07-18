package app.pokedex.pokemon.loggers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@Component
@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class PokemonLogger implements Filter {

    //File logger
    private static final Logger LOGGER = LoggerFactory.getLogger("file-logger");

    //List that holds id's of requests
    private ArrayList<String> requesters = new ArrayList<String>();

    //Run this method once per minute
    //Prints logs from last minute to console and requests.log file
    @Scheduled(initialDelayString = "PT1M", fixedRateString = "PT1M")
    void logBack(){
        System.out.println("LOG FILE UPDATED.");
        LOGGER.info(requesters.size() + " unique requests made in the last minute.\n IDS: " + requesters);
        requesters.clear();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        //Extract id parameter
        String id = httpServletRequest.getParameter("id");

        //if id is passed and it isn't in list, add it to the list
        if(id != null && !id.equals("") && !requesters.contains(id))
            requesters.add(id);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
