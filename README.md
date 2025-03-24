# Palm RQ - Layoff

## Requirement

* JDK 21

## Sample Configuration
### `local` profile
```yaml
app:
  kafka:
    bootstrap-servers: localhost:9092
  ollama:
    model: deepseek-r1:1.5b
    api-base: http://localhost:11434/api

me:
  ehp246:
    aufrest:
      restlogger:
        enabled: true
logging:
  config: classpath:log4j2-local.xml
 ```
## REST API
Root: `/v1`

Submit an article for ingestion:

`POST ingestion/article`

```json
{
    "title": "",
    "content": "**Nearly 500 workers laid off as Amscan facility closes in Orange County**\n*Story by Maria M. Silva · 1d · 2 min read*\nCHESTER—Amscan, Party City’s wholesale division, will lay off 471 workers and shut down its Orange County distribution center as the company files for Chapter 11 bankruptcy.\nThe site is scheduled to close on Friday, according to a Worker Adjustment and Retraining Notification filed last month with the state Department of Labor. Among the affected workers are material handlers, supervisors, and human resources staff, said Kristina Patsalos, a supervisor at the Orange County Employment and Training Administration. It was not immediately clear if the workers, who are not unionized, would receive severance.\n",
    "url": "",
    "date": "{{$isoTimestamp}}"
}
```
