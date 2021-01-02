#!/usr/bin/env bash


IMAGE_NAME='shinsei-bank-t-point-campaign-assign:latest'

SLEEP_SECONDS=20
TIMEOUT_SECONDS=600
START_TIME_SECONDS=$(date +%s)
ELAPSED_SECONDS=0


###########################################################################
echo -e "Check if Docker daemon running..."

while true; do

    IMAGE_ID=$(/usr/local/bin/docker image ls -q ${IMAGE_NAME} 2>&1)
    if [[ ! $IMAGE_ID =~ ^Cannot.*|^Error.* ]]; then
        echo -e "Docker daemon seems running."
        break ;
    fi

    if [[ $ELAPSED_SECONDS -ge $TIMEOUT_SECONDS ]]; then
        echo "Timeout. exit 1"
        exit 1
    fi

    echo -e "Waiting Docker daemon launching..."
    sleep $SLEEP_SECONDS

    ELAPSED_SECONDS=$(date -v-"$START_TIME_SECONDS"S +%s)
done;


###########################################################################
echo -e "Check if Docker image ${IMAGE_NAME} exists."

if [ "$IMAGE_ID" == "" ]; then
    echo -e "${IMAGE_NAME} not exists."
    echo -e "Building ${IMAGE_NAME}..."
    echo -e ""

    ./build.sh

    echo -e ""
    echo -e "${IMAGE_NAME} was built."
else
    echo -e "${IMAGE_NAME} was found."
fi


###########################################################################
echo -e "Running application..."

/usr/local/bin/docker run \
  -v $PWD/datasources:/usr/local/datasources \
  -v $PWD/logs:/usr/local/logs \
  -e TZ=Asia/Tokyo \
  --rm shinsei-bank-t-point-campaign-assign:latest


echo -e "Done."