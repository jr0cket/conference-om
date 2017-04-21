(ns conference-om.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; State for the application

;; Use defonce to define the app state avoids the state being
;; reset each time figwheel reloads the code
(defonce app-state (atom {:text "Hello Chestnut!"}))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Components

(defn conference-schedule
  "A component to display the schedule of talks for the conference
Initially just a single placeholder
Properties such as on dom/a are converted to a javascript object with #js"

  [cursor component]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
               (dom/p nil "Talk tile placeholder")
               (dom/p nil "Talk description placeholder")
               (dom/a #js {:href "https://twitter.com/jr0cket"} "@jr0cket")))))


(defn root-component [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
               (dom/h1 nil (:text app))
               (om/build conference-schedule app)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Render components with om/root

(om/root
 root-component
 app-state
 {:target (js/document.getElementById "app")})


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Code to test the app

;; Update the state by replacing the string in :text
(swap! app-state update :text #(str "Hello from the REPL"))
