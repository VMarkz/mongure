(defproject org.clojars.vmarkz/mongure "1.0.1"
            :description "A mongodb starter to use in clojure tests"
            :url "https://github.com/VMarkz/mongure"
            :license {:name "The MIT License"
                      :url "http://opensource.org/licenses/MIT"
                      :distribution :repo}
            :dependencies [[org.clojure/clojure "1.10.3"]
                           [org.mongodb/mongo-java-driver "3.12.10"]
                           [de.flapdoodle.embed/de.flapdoodle.embed.mongo "3.0.0"]]
            :aliases {:test {:extra-paths ["test"]
                             :extra-deps {lambdaisland/kaocha {:mvn/version "1.0.632"}
                                          lambdaisland/kaocha-cloverage {:mvn/version "1.0-45"}
                                          com.cognitect/test-runner {:git/url "https://github.com/cognitect-labs/test-runner.git"
                                                                     :sha "209b64504cb3bd3b99ecfec7937b358a879f55c1"}}
                             :main-opts ["-m" "cognitect.test-runner"]}}
            :paths ["src"])
