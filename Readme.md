**Simple Spring-boot application**

**Tools:**
* JDK 8
* Maven
* Springboot 2.4.3
* [Elasticsearch 7.15](https://www.elastic.co/elasticsearch/)   
* Swagger
* Junit
* Mockito

**Running apps:**
```
mvn spring-boot:run
```

**Checking on Kibana:**
```
GET flight/_mapping
GET flight/_search
{
  "query": {
    "match": {
      "flightCode": "GA303"
    }
  }
}
GET flight/_search
{
  "aggs": {
    "flight_by_month": {
      "date_histogram": {
        "field": "departure_date",
        "calendar_interval": "month",
        "format": "yyyy-MM"
      },
      "aggs": {
        "by_flightCode": {
          "terms": {
            "field": "flightCode"
          }
        }
      }
    }
  }
}
```