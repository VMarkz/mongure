(ns mongure.core
  (:gen-class)
  (:import
   [com.mongodb MongoClient]
   [de.flapdoodle.embed.mongo MongodStarter]
   [de.flapdoodle.embed.mongo.config MongodConfig Net MongoCmdOptions]
   [de.flapdoodle.embed.mongo.distribution Version]
   [de.flapdoodle.embed.process.runtime Network]))

(def mongo-state (atom nil))

(defn prepare-mongo! [port]
  (.. MongodConfig builder
      (version Version/V2_6_0)
      (net (Net. port (. Network localhostIsIPv6)))
      (cmdOptions (. (.. MongoCmdOptions builder (auth false)) build))
      (build)))

(defn start-mongo! [db-name address port]
  (when (nil? @mongo-state)
    (let [starter (. MongodStarter getDefaultInstance)
          mongod-executable (. starter prepare (prepare-mongo! port))
          _ (.start mongod-executable)
          mongo (MongoClient. address port)
          db (. mongo getDB db-name)]
      (reset! mongo-state mongod-executable)
      db)))

(defn stop-mongo! []
  (when (not (nil? @mongo-state))
    (. @mongo-state stop)
    (reset! mongo-state nil)))
