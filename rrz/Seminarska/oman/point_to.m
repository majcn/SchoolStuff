%Segmenta 2 in 3 sta enaka = dolzinaS
function [object] = point_to(regions, state)

%22DPI, 30cm, 30 delimo z 2.54(1DPI) in pomožimo z 22
dolzinaS = (30/2.54)*22;
s1 = sin(state(1));
c1 = cos(state(1));
s2 = sin(state(2));
c2 = cos(state(2));
s23 = sin(state(2)+state(3));
c23 = cos(state(2)+state(3));
%%Matrika iz prosojnic
matrikaR = [c1*c23 -c1*s23 s1 (c1*(dolzinaS*c2 + dolzinaS*c23));
			s1*c23 -s1*s23 -c1 (s1*(dolzinaS*c2 + dolzinaS*c23));
			s23 c23 0 (dolzinaS*s2 + dolzinaS*s23);
			0 0 0 1];

%Ce je robot v zgornjem levem kotu
pRoka = matrikaR * [0;0;0;1];
%pozicia roke(X in Y)
pRoka1 = [pRoka(1);pRoka(2)];

%cez vse regije
v = size(regions);

object = 0;
for i=1:v(1)
	%koordinate regije
	xmin = regions(i,1);
	xmax = regions(i,3);
	ymin = regions(i,2);
	ymax = regions(i,4);
	%pRoka(1) = X roke
	%pRoka(2) = Y roke
	%preveri, ce je roka znotraj regije in ce je, je to objekt na katerega kaze
	if(pRoka(1) > xmin && pRoka(1) < xmax && pRoka(2) > ymin && pRoka(2) < ymax)
		object = i;
	end;

end;

end;
