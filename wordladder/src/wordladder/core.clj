(ns wordladder.core
(:require [clojure.java.io :refer :all])
(:gen-class))

(def alphabet (set (map char (range 97 123))))

(defn read-word-set []
	(set (with-open [rdr (reader "../word.list")]
		(doall (line-seq rdr)
			))))

(defn find-word [word word-set]
	(contains? word-set word)
	)

(defn all-other-letters [letter]
	(disj alphabet letter)
	)

(defn generate-next-words [word]
	(let [letters (apply vector word)]
		(for [i (range 0 (count letters))
			  replacement (all-other-letters (nth letters i))]
			(apply str (assoc letters i replacement)))
		))
(defn filter-words-in-list [listGen listOrg]
   (filter listOrg listGen)

	)