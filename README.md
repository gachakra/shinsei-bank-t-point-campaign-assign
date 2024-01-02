This app doesn't work anymore. It had worked until a certain point but then scraping the website from the app seemed to be banned by the bank.

I think I've earned more than 10 T-points. That is totally waste of time (but with fun).


# shinse-bank-t-point-campaign-assign

This app automatically visits [Shinsei Bank T-point campaign page](https://webform.shinseibank.com/webapp/form/19913_xldb_4/index.do?lid=p) and assigns the campaign every first day of month via OS X Launch Agent. Now you can earn what little T-points.

## Quick start
### 1. Run init-script
```bash
# This script copies YAML configuration files
# and creates and loads plist file as OSX Launch Agent 
./init.sh
```
### 2. Then configure your settings on copied yml files below
- `src/main/resources/form.yml`
  ```yaml
  form:
    userAgent: 
    account: 
    tPointNumber16Digits:
    birthDate:
    email:
  ```
- `src/main/resources/slack.yml` (Optional)
  
  Create Slack app for reporting [here](https://api.slack.com/apps)
   ```yaml
   channelId:
   botToken: 
   ```

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
