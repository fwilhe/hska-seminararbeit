
DOCUMENT=seminararbeit_florian_wilhelm_36873


doc:
	pdflatex $(DOCUMENT).tex
	bibtex $(DOCUMENT)
	makeglossaries $(DOCUMENT)
	pdflatex $(DOCUMENT).tex
	pdflatex $(DOCUMENT).tex

fast:
	pdflatex $(DOCUMENT).tex

clean:
	rm *aux *glg *glo *gls *ist *lof *log *out *toc *pyg *lol *blg *bbl *~

cleanall:
	rm *aux *glg *glo *gls *ist *lof *log *out *toc *pyg *pdf *lol *blg *bbl *~

#Only for spell-checking!
wordfile:
	pandoc -s $(DOCUMENT).tex -o $(DOCUMENT).docx
	
run:
	xdg-open $(DOCUMENT).pdf
