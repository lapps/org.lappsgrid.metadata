BRANCH=$(shell git branch | grep \* | cut -d\  -f2)
GROOVYDOC=src/main/scripts/lappsdoc

help:
	@echo "Help is needed..."
	
clean:
	mvn clean
	
jar:
	mvn package
	
deploy:
	pom ; if [ $$? -eq 1 ] ; then exit 1 ; fi
	mvn javadoc:jar source:jar deploy

snapshot:
	issnapshot ; if [ $$? -eq 1 ] ; then exit 1 ; fi
	mvn javadoc:jar source:jar deploy

docs:
	if [ -e target/apidocs ] ; then rm -rf target/apidocs ; fi
	$(GROOVYDOC) "Lappsgrid Metadata"

site:
	git stash
	if [ -e target/apidocs ] ; then rm -rf target/apidocs ; fi
	$(GROOVYDOC) "Lappsgrid Metadata"
	git checkout gh-pages
	rm -f *.html *.ico *.gif
	rm -rf org
	git commit -m "Removed old files."
	cp -r target/apidocs/* .	
	git add *.html *.ico *.gif org
	git commit -a -m "Updated gh-pages."
	git push origin gh-pages
	git checkout $(BRANCH)
	git stash apply

test:
	echo "Branch is $(BRANCH)"

