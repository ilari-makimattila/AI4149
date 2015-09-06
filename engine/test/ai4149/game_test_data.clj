(ns ai4149.game-test-data 
  (:require [midje.sweet :refer :all]
            [ai4149.messages :refer :all]))


(def simple-test-state
  #ai4149.messages.FullGameState
  {:turn 0 
   :turns 100
   :rules [#ai4149.messages.UnitRule
           {:name "Commander"
            :type :commander
            :speed 5
            :armor 10
            :cost nil
            :build-time nil
            :built-by nil
            :shape [[-5 -5] [5 -5] [5 5] [-5 5]]
            :weapons {:main #ai4149.messages.WeaponRule
                            {:type :projectile
                             :range 50
                             :velocity 25
                             :damage 20}}}
           #ai4149.messages.UnitRule
           {:name "Lt. Commander"
            :type :harvester
            :speed 10
            :armor 1
            :cost 500
            :build-time 10
            :built-by :factory
            :shape [[-4 -4] [4 -4] [4 4] [-4 4]]}
           #ai4149.messages.UnitRule
           {:name "Tank"
            :type :tank
            :speed 10
            :armor 1
            :cost 100
            :build-time 5
            :built-by :factory
            :shape [[-2 -2] [2 -2] [2 2] [-2 2]]
            :weapons {:main #ai4149.messages.WeaponRule
                            {:type :projectile
                             :range 40
                             :velocity 20
                             :damage 10}}}
           #ai4149.messages.UnitRule
           {:name "Factory"
            :type :factory
            :speed 0
            :armor 10
            :cost 2000
            :build-time 5
            :built-by :commander
            :shape [[-10 -10] [10 -10] [10 10] [-10 10]]}]
   :player-states
   [#ai4149.messages.PlayerState
    {:player "player-1"
     :resources 2000
     :unit-states [#ai4149.messages.UnitState
                   {:id "p1-commander"
                    :type :commander
                    :position #ai4149.messages.Coordinates[30 12]
                    :health 100
                    :action :idle
                    :action-coordinates nil}]
     :building-states [#ai4149.messages.BuildingState{:id "p1-b1" 
                                                      :type :factory
                                                      :position #ai4149.messages.Coordinates[10 15]
                                                      :health 100
                                                      :action :idle
                                                      :action-args nil}]}
    #ai4149.messages.PlayerState
    {:player "player-2"
     :resources 1500
     :unit-states [#ai4149.messages.UnitState
                   {:id "p2-u1"
                    :type :commander
                    :position #ai4149.messages.Coordinates[160 200]
                    :health 100
                    :action :idle
                    :action-coordinates nil}
                   #ai4149.messages.UnitState
                   {:id "p2-tank-1"
                    :type :tank
                    :position #ai4149.messages.Coordinates[70 50]
                    :health 100
                    :action :idle
                    :action-coordinates nil}]
     :building-states [#ai4149.messages.BuildingState{:id "p2-b1" 
                                                      :type :factory
                                                      :position #ai4149.messages.Coordinates[100 150]
                                                      :health 100
                                                      :action :idle
                                                      :action-args nil}]}]})


