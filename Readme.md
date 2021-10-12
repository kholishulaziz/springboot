**Simple Spring-boot application**

Integrate Spring-boot apps with Elasticsearch


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

**View the Mapping:** determines how a document and its fields are indexed and stored by defining the type of each field.
```
Syntax:
GET enter_name_of_the_index_here/_mapping
Example:
GET flight/_mapping
```
**Queries:** retrieve documents that match the criteria.
```
Syntax:
GET enter_name_of_the_index_here/_search
Example:
GET flight/_search
{
  "query": {
    "match": {
      "flightCode": "GA303"
    }
  }
}
```
**Aggregations:** summarizes your data as metrics, statistics, and other analytics.
```
Syntax:
GET enter_name_of_the_index_here/_search
{
  "aggs": {
    "name your aggregation here": {
      "specify aggregation type here": {
        "field": "name the field you want to aggregate here",
        "size": state how many buckets you want returned here
      }
    }
  }
}
Example:
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