(ns backend.collision-test
  (:require [midje.sweet :refer :all]
            [backend.messages :refer :all]
            [backend.game :as game]
            [backend.collisions :as collisions]
            [backend.game-test-data :refer [simple-test-state]]))

(facts "shape conversion"
  (let [origo #backend.messages.Coordinates[50 50]
        rectangle-shape [[10 -5] [10 5] [-10 5] [-10 -5]]]
    (fact "coordinates and shape is converted to area"
      (collisions/shape->area rectangle-shape origo) => [[60 45] [60 55] [40 55] [40 45]])))

(facts "area scaling"
  (let [area [[60 45] [60 55] [40 55] [40 45]]]
    (fact "area expands"
      (collisions/scale-area area 1) => [[61 44] [61 56] [39 56] [39 44]])
    (fact "area shrinks"
      (collisions/scale-area area -1) => [[59 46] [59 54] [41 54] [41 46]])))

(facts "area free"
  (let [free-area [[40 40] [40 50] [50 50] [50 40]]
        occupied-area [[10 20] [10 42] [32 42] [32 20]]]
    (fact "free area is detected"
      (collisions/area-free? simple-test-state free-area) => truthy)
    (fact "occupied area is detected"
      (collisions/area-free? simple-test-state occupied-area) => falsey)))


(facts "coord intersects"
  (fact "coordinates intersect with object"
    (collisions/coord-intersects? [[60 45] [60 55] [40 55] [40 45]] #backend.messages.Coordinates[50 50]) => truthy
    (collisions/coord-intersects? [[60 45] [60 55] [40 55] [40 45]] #backend.messages.Coordinates[60 55]) => truthy
    (collisions/coord-intersects? [[60 45] [60 55] [40 55] [40 45]] #backend.messages.Coordinates[60 50]) => truthy)
  (fact "coordinates do not intersect with object"
    (collisions/coord-intersects? [[60 45] [60 55] [40 55] [40 45]] #backend.messages.Coordinates[10 10]) => falsey
    (collisions/coord-intersects? [[60 45] [60 55] [40 55] [40 45]] #backend.messages.Coordinates[60 0]) => falsey
    (collisions/coord-intersects? [[60 45] [60 55] [40 55] [40 45]] #backend.messages.Coordinates[61 50]) => falsey))

(facts "area intersects"
  (let [area [[60 45] [60 55] [40 55] [40 45]]]
    (fact "areas intersect with each other"
      (collisions/area-intersects? area area) => truthy
      (collisions/area-intersects? area [[55 50] [55 55] [50 55] [50 50]]) => truthy
      (collisions/area-intersects? area [[50 50] [51 51] [50 51] [51 50]]) => truthy)
    (fact "areas do not intersect with each other"
      (collisions/area-intersects? area [[600 450] [600 550] [400 550] [400 450]]) => falsey
      (collisions/area-intersects? area [[60 56] [60 60] [40 60] [40 56]]) => falsey
      (collisions/area-intersects? area [[61 45] [61 55] [70 55] [70 45]]) => falsey)))
 


