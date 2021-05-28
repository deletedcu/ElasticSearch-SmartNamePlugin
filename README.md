# Elasticsearch SmartNamePlugin
<p align="center">
 <img width="100px" src="https://res.cloudinary.com/anuraghazra/image/upload/v1594908242/logo_ccswme.svg" align="center" alt="GitHub Readme Stats" />
 <h2 align="center">GitHub Readme Stats</h2>
 <p align="center">Get dynamically generated GitHub stats on your readmes!</p>
</p>
<p align="center">
  <img src="https://img.shields.io/badge/Supported%20by-ElasticSearch%20Power%20User%20%E2%86%92-gray.svg?colorA=655BE1&colorB=4F44D6&style=for-the-badge"/>
</p>

Explain the use case of this processor in a TLDR fashion.

## Usage


```
PUT _ingest/pipeline/SmartNamePlugin
{
  "description": "A pipeline to do whatever",
  "processors": [
    {
      "{{ cookiecutter.processor_type | replace("-", "_") }}" : {
        "field" : "my_field"
      }
    }
  ]
}

PUT /my-index/my-type/1?pipeline=SmartNamePlugin
{
  "my_field" : "Some content"
}

GET /my-index/my-type/1
{
  "my_field" : "Some content"
  "potentially_enriched_field": "potentially_enriched_value"
}
```

## Configuration

| Parameter | Use |
| --- | --- |
| some.setting   | Configure x |
| other.setting  | Configure y |

## Setup

In order to install this plugin, you need to create a zip distribution first by running

```bash
gradle clean check
```

This will produce a zip file in `build/distributions`.

After building the zip file, you can install it like this

```bash
bin/elasticsearch-plugin install file:///path/to/SmartNamePlugin/build/distribution/SmartNamePlugin-0.0.1-SNAPSHOT.zip
```

## Bugs & TODO

* There are always bugs
* and todos...

## 🌱 Technologies and Frameworks
<p>
    <!-- Java -->
    <img src="https://img.shields.io/badge/Java-007396?flat=plastic&logo=java&logoColor=white" height="32" alt="Java" />
    &nbsp;
    <!-- ElasticSearch -->
    <img src="https://img.shields.io/badge/ElasticSearch-005531?flat=plastic&logo=elasticsearch&logoColor=white" height="32" alt="ElasticSearch" />
    &nbsp;
    <!-- Logstash -->
    <img src="https://img.shields.io/badge/Logstash-005571?flat=plastic&logo=logstash&logoColor=white" height="32" alt="Logstash" />
    &nbsp;
    <!-- Kibana -->
    <img src="https://img.shields.io/badge/Kibana-005571?flat=plastic&logo=kibana&logoColor=white" height="32" alt="Kibana" />
    &nbsp;
    <!-- Android Studio -->
    <img src="https://img.shields.io/badge/Android%20Studio-3ddc84?flat=plastic&logo=android%20studio&logoColor=white" height="32" alt="Android Studio" />
    &nbsp;
</p>
