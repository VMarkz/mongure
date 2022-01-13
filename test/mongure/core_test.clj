(ns mongure.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [mongure.core :as mongure])
  (:import [de.flapdoodle.embed.mongo.config ImmutableMongodConfig]
           [de.flapdoodle.embed.process.runtime Network]
           [com.mongodb DB]))

(deftest prepare-mongo!-test
  (testing "Preparing mongure"
    (let [port (. Network getFreeServerPort)
          output (mongure/prepare-mongo! port)]
      (is (instance? ImmutableMongodConfig output)))))

(deftest start-mongo!-test
  (testing "Starting mongure"
    (let [db-name "test-db"
          address "127.0.0.1"
          port (. Network getFreeServerPort)
          output (mongure/start-mongo! db-name address port)]
      (is (instance? DB output)))))

(deftest stop-mongo!-test
  (testing "Starting mongure after start"
    (let [output (mongure/stop-mongo!)]
      (is (nil? output)))))
