(ns conference-om.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [conference-om.core-test]
   [conference-om.common-test]))

(enable-console-print!)

(doo-tests 'conference-om.core-test
           'conference-om.common-test)
