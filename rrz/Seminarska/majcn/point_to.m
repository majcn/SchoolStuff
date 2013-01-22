%vrne index na objekt kjer je nasa roka
%parameter: regions (dobimo s pomocjo funkcije recognize)
%parameter: state (vektor s tremi koti nase roke)
%return: index objekta
function [object] = point_to(regions, state)
	%geometrijski model
	dolzinaSegmenta = (30/2.54)*22; %1DPI = 2.54cm; 30cm / 2.54cm * 22DPI
	s1 = sin(state(1));
	c1 = cos(state(1));
	s23 = sin(state(2)+state(3));
	c23 = cos(state(2)+state(3));
	tMatrikaRoke = [c1*c23 -c1*s23 s1 c1*dolzinaSegmenta*(c2+c23); s1*c23 -s1*s23 -c1 s1*dolzinaSegmenta*(c2+c23); s23 c23 0 dolzinaSegmenta*(s2+s23); 0 0 0 1];
	%recimo da je robot v zgornjem levem kotu:
	polozajRoke = tMatrikaRoke * [0;0;0;1];
	polozajRokeXY = [polozajRoke(1:2)];
	
	%dolocanje predmeta
	minIndex = 0;
	minRazdalja = 0;
	for i=1:size(regions)(1)
		%dobimo koordinate in center nasega objekta
		x1 = regions(i, 1);
		y1 = regions(i, 2);
		x2 = regions(i, 3);
		y2 = regions(i, 4);
		center = [(x1+x2)/2, (y1+y2)/2];
		%center zamaknemo v 0,0 ter dobimo nove koordinate robotske roke
		objectRokaXY = polozajRokeXY-center;
		%moj algoritem dela na principu najmanjse razdalje od center do objectRokaXY ter na ta nacin doloci index (to sem si izbral zaradi moznosti manjsega prekrivanja BoundingBoxov)
		if((minRazdalja == 0) && (objectRokaXY(1)<x2) && (objectRokaXY(1)>x1) && (objectRokaXY(2)<y2) && (objectRokaXY(2)>y1))
			minIndex = i;
			minRazdalja = sqrt(sum(objectRokaXY.^2));
		else
			iRazdalja = sqrt(sum(objectRokaXY.^2));
			if((iRazdalja < minRazdalja) && (objectRokaXY(1)<x2) && (objectRokaXY(1)>x1) && (objectRokaXY(2)<y2) && (objectRokaXY(2)>y1))
				minIndex = i;
				minRazdalja = iRazdalja;
			endif
		endif
	end
	object = minIndex;
end