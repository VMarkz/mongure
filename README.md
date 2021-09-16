<h1>Mongure</h1>

<!-- <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Clojure_logo.svg/1200px-Clojure_logo.svg.png" width="100px"/>-->

<h2>Motivation</h2>
<p>I was writing unit tests with MongoDb, but I always needed to start the database manually with a docker-compose file.começar  </p>
<p>This lib came to solve this! </p>

<h2>Instalation</h2>

[![Clojars Project](https://clojars.org/org.clojars.vmarkz/mongure/latest-version.svg)](https://clojars.org/org.clojars.vmarkz/mongure)

<h2>Usage</h2>

```clojure
;; In your ns statement:
(ns my.ns
  (:require [mongure.core :as mg]))

;; To start:
  (mg/start-mongo! "test-db" "localhost" 27017)
;; The parameters sequence here are, the database name, address and port:
  
;; To stop:
  (mg/stop-mongo!)
```
