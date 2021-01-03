# shinse-bank-t-point-campaign-assign

This app automatically visits Shinsei Bank T-point campaign page and assigns the campaign every first day of month via OS X Launch Agent. Now you can earn what little T-points.

## Quick start
### 1. Run init-script
```bash
# This script copies YAML configuration files
# and creates and loads plist file as OSX Launch Agent 
./init.sh
```
### 2. Then configure your settings on copied yml files below
- `src/main/resources/form.yml`
- `src/main/resources/slack.yml`

## While developing
### Build application Docker image
```bash
./build.sh
```
### Run application via Docker container
```bash
./run.sh
```
### Build application Docker image and run it.
```bash
./build-run.sh
```