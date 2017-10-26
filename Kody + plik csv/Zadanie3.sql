ALTER TABLE statuses
ADD dzien date;

UPDATE statuses
SET dzien=kontakt_ts;

INSERT INTO `f_docieralnosc`(`data`, `sukcesy`, `utraty`, `do_ponowienia`)
SELECT dzien, 
COUNT(CASE status WHEN "zainteresowany" THEN 1 ELSE null END), 
COUNT(CASE status WHEN "niezainteresowany" THEN 1 ELSE null END), 
COUNT(CASE status WHEN "poczta_glosowa" THEN 1 WHEN "nie_ma_w_domu" THEN 1 ELSE null END) 
FROM statuses 
GROUP BY dzien;