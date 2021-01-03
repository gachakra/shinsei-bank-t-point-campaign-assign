#!/usr/bin/env bash

PLIST_NAME="dev.notonza.ShinseiBankTPointCampaignAssign"
PLIST="$PLIST_NAME.plist"


###########################################################################
echo -e "Copying example file of YAML configuration files"

cp -an src/main/resources/form.yml.example src/main/resources/form.yml
cp -an src/main/resources/slack.yml.example src/main/resources/slack.yml

echo -e "Please configure settings on src/main/resources/form.yml"
echo -e "Please configure settings on src/main/resources/slack.yml"

###########################################################################
echo -e "Copying example file of $PLIST"

cp -a \
    ./"$PLIST".example \
    ./"$PLIST"

sed -i '' \
    -e "s|\${USER}|$USER|g; s|\${PROJECT_ROOT}|$PWD|g" \
    ./"$PLIST"


###########################################################################
echo -e "Linking $PLIST in ~/Library/LaunchAgents/"

ln -sf "$PWD/$PLIST" \
    ~/Library/LaunchAgents/"$PLIST"


###########################################################################
echo -e "Loading ~/Library/LaunchAgents/$PLIST"

launchctl unload ~/Library/LaunchAgents/"$PLIST"
launchctl load ~/Library/LaunchAgents/"$PLIST"


if [[ $(launchctl list | grep dev.notonza.ShinseiBankTPointCampaignAssign) == "-	0	$PLIST_NAME" ]]; then
    echo -e "~/Library/LaunchAgents/$PLIST was successfully loaded."
else
    echo -e "Something went wrong while loading ~/Library/LaunchAgents/$PLIST"
fi

# launchctl start ~/Library/LaunchAgents/"$PLIST"