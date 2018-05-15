(ns ingesolvoll.main
  (:require [leiningen.core.main :as lein]))

(defn main [& args]
      (with-redefs [lein/exit (constantly nil)]
                   (apply lein/-main args)))