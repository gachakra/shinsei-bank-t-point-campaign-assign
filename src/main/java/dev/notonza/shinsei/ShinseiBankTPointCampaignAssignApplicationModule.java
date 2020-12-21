package dev.notonza.shinsei;

import com.google.inject.AbstractModule;
import dev.notonza.shinsei.config.FormConfiguration;
import dev.notonza.shinsei.config.LoadConfiguration;
import dev.notonza.shinsei.config.SlackConfiguration;

/**
 * @author gachakra
 * Created on 2020/12/02.
 */
final public class ShinseiBankTPointCampaignAssignApplicationModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(FormConfiguration.class)
            .toInstance(new LoadConfiguration<>("form.yml", FormConfiguration.class)
                .execute());

        bind(SlackConfiguration.class)
            .toInstance(new LoadConfiguration<>("slack.yml", SlackConfiguration.class)
                .execute());
    }
}