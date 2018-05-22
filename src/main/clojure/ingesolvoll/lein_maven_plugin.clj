(ns ingesolvoll.lein-maven-plugin
  (:require [leiningen.core.main :as lein])
  (:import (org.apache.maven.plugin MojoExecutionException)))

(defn main [command]
      (let [args (re-seq #"\w+" command)]

           (when (-> args first (= "clean"))
                 (throw (MojoExecutionException. "This plugin cannot be used for cleaning project. Configure maven to do that.")))
           (with-redefs [lein/exit (constantly nil)]
                        (apply lein/-main args))))