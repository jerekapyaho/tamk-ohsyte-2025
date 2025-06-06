package tamk.ohsyte.commands;

import java.util.List;
import java.util.Set;

import picocli.CommandLine.Command;
import tamk.ohsyte.EventManager;

@Command(name = "listproviders")
public class ListProviders implements Runnable {

    @Override
    public void run() {
        //System.out.println("Listing event provider IDs");
        EventManager manager = EventManager.getInstance();

        Set<String> providerIds = manager.getEventProviderIdentifiers();
        for (String id : providerIds) {
            System.out.println(id);
        }
    }
}
