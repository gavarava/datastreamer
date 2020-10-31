# Setup Datastreamer on Google Cloud Platform
### Connect gcloud cli to the account in Google Cloud
```
gcloud auth login
gcloud config set project total-media-204920
```
### Start - Stop - Restart Postgres Database
```
gcloud sql instances patch datastreamer-311020 --activation-policy ALWAYS

gcloud sql instances patch datastreamer-311020 --activation-policy NEVER

gcloud sql instances restart datastreamer-311020
```
### Enable connection via Cloud sql proxy
```
cloud_sql_proxy -instances=total-media-204920:europe-north1:datastreamer-311020=tcp:5432 &&
```
### Connect to database
```
gcloud sql connect datastreamer-311020 --user=postgres
```