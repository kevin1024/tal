# tal

A Clojure program that scrapes the This American Life site to generate an RSS feed with all this year's episodes, because the official RSS feed only has the latest episode.  Sorry Ira.

![tal](http://graphics8.nytimes.com/images/2007/03/22/arts/television/22heff600.jpg)

## Usage

Run locally:

```bash
lein run 8080
```

This will start a server on your local machine, port 8080.

## Deploy

This project is intended to be deployed on Heroku.

```bash
heroku app:create
git push heroku master
```

## Release history

* 0.0.3 - Don't return the first epsiode if it hasn't been published yet
* 0.0.2 - Add date parsing
* 0.0.1 - Initial release

## License

Copyright © 2013 Kevin McCarthy

Distributed under the MIT license
