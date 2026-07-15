(ns statute.facts
  "General-law compliance catalog for Uruguay (URY). Unlike most sibling
  cloud-itonami-iso3166-* repos, this repo had no `marketentry.facts`
  implementation yet (blueprint-only) -- this is the FIRST code-bearing
  content in this repo, self-contained with its own deps.edn. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/-chl/-arg/-zaf/-col's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Peru was attempted first this tick and abandoned entirely: gob.pe
  returned HTTP 418 (a deliberate bot-block status), diariooficial.
  elperuano.pe was unreachable (connect ECONNREFUSED), spijweb.minjus.
  gob.pe reset the connection (ECONNRESET), and two independent PDF
  mirrors (essalud.gob.pe and docs.peru.justia.com) both rendered as
  completely illegible box-glyphs -- even their titles unreadable, the
  worst PDF-garbling case seen in this family so far. None of that was
  used as a citation.

  Every entry here instead cites an OFFICIAL impo.com.uy (IMPO --
  Uruguay's Centro de Información Oficial, the government body that
  publishes and maintains the official legislative database) URL --
  never fabricated. A law not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries."
  {"URY"
   [{:statute/id "ury.ley-sociedades-comerciales-16060"
     :statute/title "Ley N.º 16.060 (Ley de Sociedades Comerciales)"
     :statute/jurisdiction "URY"
     :statute/kind :law
     :statute/law-number "Ley N.º 16.060"
     :statute/url "https://www.impo.com.uy/bases/leyes/16060-1989"
     :statute/url-provenance :official-impo-com-uy
     :statute/enacted-date "1989-09-04"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "ury.ley-proteccion-datos-personales-18331"
     :statute/title "Ley N.º 18.331 (Ley de Protección de Datos Personales)"
     :statute/jurisdiction "URY"
     :statute/kind :law
     :statute/law-number "Ley N.º 18.331"
     :statute/url "https://www.impo.com.uy/bases/leyes/18331-2008"
     :statute/url-provenance :official-impo-com-uy
     :statute/enacted-date "2008-08-11"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "ury.ley-ocho-horas-5350"
     :statute/title "Ley N.º 5.350 (Ley de las Ocho Horas)"
     :statute/jurisdiction "URY"
     :statute/kind :law
     :statute/law-number "Ley N.º 5.350"
     :statute/url "https://www.impo.com.uy/bases/leyes/5350-1915"
     :statute/url-provenance :official-impo-com-uy
     :statute/enacted-date "1915-11-17"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:labor :employment}}]})

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
      :note (str "cloud-itonami-iso3166-ury statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "URY")) " URY statutes seeded with an "
                 "official impo.com.uy citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
