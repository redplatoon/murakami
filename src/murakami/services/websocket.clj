(ns murakami.services.websocket ; .clj
  (:require [clojure.string :as string]
            [taoensso.sente :as sente]
            [taoensso.sente.server-adapters.http-kit
             :refer (sente-web-server-adapter)]))


(defn user-id-fn
  [ring-req]
  ;;probably not the best way to handle this..
  (let [split-qry (string/split
                    (get ring-req :query-string) #"=")]
                      (cond
                        (> (count split-qry) 1) (get split-qry 1)
                        :else                   nil)))
         :csrf-token-fn
          (fn [ring-req] "23lrkjl3krj2lrkj2lrkj23lrkj23lr")

(let [{:keys [ch-recv send-fn ajax-post-fn ajax-get-or-ws-handshake-fn
              connected-uids]}
      (sente/make-channel-socket! sente-web-server-adapter {:user-id-fn user-id-fn})]
  (def ring-ajax-post                ajax-post-fn)
  (def ring-ajax-get-or-ws-handshake ajax-get-or-ws-handshake-fn)
  (def ch-chsk                       ch-recv) ; ChannelSocket's receive channel
  (def chsk-send!                    send-fn) ; ChannelSocket's send API fn
  (def connected-uids                connected-uids) ; Watchable, read-only atom
  )
