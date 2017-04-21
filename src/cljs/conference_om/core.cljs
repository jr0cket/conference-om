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

(defn root-component [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil (dom/h1 nil (:text app))))))

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
