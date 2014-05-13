DOCUMENT=seminararbeit_folien_florian_wilhelm_36873

doc:
	pdflatex $(DOCUMENT).tex
	pdflatex $(DOCUMENT).tex
	pdflatex $(DOCUMENT).tex

fast:
	pdflatex $(DOCUMENT).tex

clean:
	rm *aux *glg *glo *gls *ist *lof *log *out *toc *pyg *snm *nav

cleanall:
	rm *aux *glg *glo *gls *ist *lof *log *out *toc *pyg *snm *nav *pdf
	
run:
	xdg-open $(DOCUMENT).pdf
