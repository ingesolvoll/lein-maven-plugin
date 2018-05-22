(ns ingesolvoll.lein-maven-plugin
  (:require [leiningen.core.main :as lein])
  (:import (org.apache.maven.plugin MojoExecutionException)))

(defn exit
  ([exit-code & msg]
   (when-not (= exit-code 0)
     (throw (ex-info (if (seq msg)
                       (apply print-str msg)
                       "Suppressed exit")
                     {:exit-code exit-code :suppress-msg (empty? msg)}))))
  ([] (exit 0)))

(defn main [command]
  (let [args (re-seq #"[^\s]+" command)]

    (when (-> args first (= "clean"))
      (throw (MojoExecutionException. "This plugin cannot be used for cleaning project. Configure maven to do that.")))
    (with-redefs [lein/exit exit]
      (apply lein/-main args))))