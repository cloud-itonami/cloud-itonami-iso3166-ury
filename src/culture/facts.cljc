(ns culture.facts
  "Country-level regional-culture catalog for Uruguay (URY) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"URY"
   [{:culture/id "ury.dish.chivito"
     :culture/name "Chivito"
     :culture/country "URY"
     :culture/kind :dish
     :culture/summary "Uruguay's national dish: a sandwich of sliced beefsteak (churrasco), mozzarella, ham, tomatoes, mustard, mayonnaise and olives, created in 1946 at a Punta del Este restaurant when the owner substituted beef for goat meat to fulfil a customer's request."
     :culture/url "https://en.wikipedia.org/wiki/Chivito_(sandwich)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ury.dish.asado"
     :culture/name "Asado"
     :culture/country "URY"
     :culture/kind :dish
     :culture/summary "Barbecue technique and social event especially significant in Argentina and Uruguay, also practiced in Brazil, Chile and Paraguay; in Uruguay the asado is grilled directly over embers or hot coals rather than the charcoal method used elsewhere."
     :culture/url "https://en.wikipedia.org/wiki/Asado"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ury.dish.chaja"
     :culture/name "Chajá"
     :culture/country "URY"
     :culture/kind :dish
     :culture/summary "Typical dessert of Uruguayan cuisine created in 1927 by Orlando Castellano in Paysandú, consisting of meringue, sponge cake, cream and fruits; has become an exported Uruguayan culinary icon."
     :culture/url "https://en.wikipedia.org/wiki/Chaj%C3%A1"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ury.beverage.yerba-mate"
     :culture/name "Yerba mate"
     :culture/country "URY"
     :culture/kind :beverage
     :culture/summary "Traditional infused drink of central and southern South America; Uruguay is the largest per-capita consumer at 10 kg per person annually (versus Argentina's 5 kg), sharing the communal drinking custom with Paraguay, Argentina, southern Brazil and southern Chile."
     :culture/url "https://en.wikipedia.org/wiki/Yerba_mate"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ury.festival.uruguayan-carnival"
     :culture/name "Uruguayan Carnival"
     :culture/country "URY"
     :culture/kind :festival
     :culture/summary "Festival taking place every year in Uruguay from mid-January to late February, considered the longest carnival in the world; its artistic foundation draws root from candombe, murga and tablados, forms of expression of Uruguayan culture through dance and music."
     :culture/url "https://en.wikipedia.org/wiki/Uruguayan_Carnival"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ury.heritage.colonia-del-sacramento"
     :culture/name "Colonia del Sacramento"
     :culture/country "URY"
     :culture/kind :heritage
     :culture/summary "City in southwestern Uruguay by the Río de la Plata whose historic quarter earned UNESCO World Heritage designation in 1995 for its distinctive Portuguese colonial architecture and urban design."
     :culture/url "https://en.wikipedia.org/wiki/Colonia_del_Sacramento"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-ury culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "URY"))
                 " URY entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
