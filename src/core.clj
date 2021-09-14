(ns core
  (:import
   [com.mongodb BasicDBObject MongoClient]
   [com.mongodb.client MongoCollection MongoDatabase]
   [de.flapdoodle.embed.mongo MongodExecutable MongodProcess MongodStarter]
   [de.flapdoodle.embed.mongo.config MongodConfig Net]
   [de.flapdoodle.embed.mongo.distribution Version]
   [de.flapdoodle.embed.process.runtime Network]
   [java.util Date]))

(def mongo-state (atom nil))

(defn- prepare-mongo! [port]
  (let [builder (. MongodConfig builder)
        builder-with-version (. builder version Version/V2_6_0)
        builder-with-net (. builder-with-version net (Net. port (. Network localhostIsIPv6)))
        mongod-config (. builder-with-net build)]
    mongod-config))

(defn start-mongo! [db-name addres port]
  (when (nil? @mongo-state)
    (let [starter (. MongodStarter getDefaultInstance)
          mongod-executable (reset! mongo-state (. starter prepare (prepare-mongo! port)))
          mongod (.start mongod-executable)]
         (let [mongo (MongoClient. addres port)
               db (. mongo getDB db-name)
               col (. db createCollection "testCol" (BasicDBObject.))]
           (. col save (BasicDBObject. "testDoc" (Date.)))))))

(defn stop-mongo! []
  (when (not (nil? @mongo-state))
    (. @mongo-state stop)))
