# Reddit Search API

Simple wrapper for the [Reddit search API](http://www.reddit.com/dev/api#GET_search).

## Usage

Include this as a Maven dependency:

    <repositories>
      <repository>
        <id>icrawl-releases</id>
        <url>http://maven.l3s.uni-hannover.de:9088/nexus/content/repositories/icrawl_release/</url>
      </repository>
    </repositories>
    <!-- ... -->
    <dependency>
      <groupId>de.l3s.icrawl</groupId>
      <artifactId>reddit-search-api</artifactId>
      <version>0.1</version>
    </dependency>

Create a new instance and start queries:

    RedditSearchApi reddit = new RedditSearchApi();
    List<Link> links = reddit.search("query", 50, Sort.RELEVANCE);

## Author
Gerhard Gossen <gossen@l3s.de>, [L3S Research Center](http://www.l3s.de/).

## License

Copyright 2014 Gerhard Gossen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.