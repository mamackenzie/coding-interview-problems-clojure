;;************************************************************************
;; Solutions to some programming problems from the book "Cracking the
;; Coding Interview" by Gayle Laakmann McDowell.
;;
;; Written by Michael Mackenzie <mike.a.mackenzie@gmail.com>
;;************************************************************************

(ns coding-interview-problems-clojure.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn partition-by-pred [pred coll]
  "Partitions the given collection into all elements for which the given predicate
  returns true and those for which it returns false. Returns a 2-element vector
  containing the 'true' partition followed by the 'false' one."
  (let [partitioned (group-by pred coll)]
    [(partitioned true) (partitioned false)]))


;; Chapter 1 - Arrays and Strings

(defn check-permutation
  "1.2 - Check Permutation
  Given two strings, write a method to decide if one is a permutation of the other."
  [s1 s2]
  (= (sort s1) (sort s2)))

(defn string-rotation
  "1.9 - String Rotation
  Assume you have a method `isSubString` which checks if one word is a substring of another.
  Given two strings, `s1` and `s2`, write code to check if `s2` is a rotation of `s1` using
  only one call to `isSubString` (e.g. 'waterbottle' is a rotation of 'erbottlewat')."
  [s1 s2]
  (and
    (= (count s1) (count s2))
    ;; `str/includes?` is Clojure's `isSubString`
    (str/includes? (str s1 s1) s2)))


;; Chapter 8 - Recursion and Dynamic Programming

(defn triple-step
  "8.1 - Triple Step
  A child is running up a staircase with n steps and can hop either 1 step, 2 steps,
  or 3 steps at a time. Implement a method to count how many possible ways a child can run
  up the stairs."
  ([n incomplete-permutations valid-permutations]
   (let [[new-valid-permutations in-progress] (partition-by-pred
                                                #(= n (reduce + %))
                                                (for [next-step [1 2 3]
                                                      ip incomplete-permutations
                                                      :when (<= (reduce + next-step ip) n)]
                                                  (conj ip next-step)))
         valid-permutations (into valid-permutations new-valid-permutations)]
     (if (seq in-progress)
       (recur n in-progress valid-permutations)
       valid-permutations)))
  ([n] (triple-step n [[1] [2] [3]] [])))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
