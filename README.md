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
    "content": "Return in JSON the number of laid-off employees, the company name as company, the location of the layoffs as location for the following text:\"Israeli cyber firm ActiveFence, which monitors malicious content online, is laying off 22 employees, representing 7% of its workforce, with an estimated half of them in Israel. ActiveFence's technology identifies and tracks malicious activities online at scale. By scanning in hidden sources of chatter and across the web, the company safeguards against bad actors, the content they share, and the networks they operate, protecting billions of people worldwide from violent extremism, disinformation, child sexual abuse, fraud, and other harms of the internet. The company said, \"Like every other company in the market, ActiveFence is constantly working to streamline and improve its operations. As part of this process, we have made the difficult decision to part ways with a small number of employees. However, the vast majority of the team continues to lead the company forward and fulfill our mission—to make the digital world safer for everyone. ActiveFence continues to grow and recruit employees for various positions around the world.\"\"",
    "url": "",
    "date": "{{$isoTimestamp}}"
}
```
