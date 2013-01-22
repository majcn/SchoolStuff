%function [matrika] = showcircles(imgin,C)
function [] = showcircles(imgin, N)
C = circles(imgin,N);
NOP = 1000; %stevilo tock na krogu
%spremenimo v matriko

for i=1:N
	matrika(i,1) = C(i);
	matrika(i,2) = C(i+N);
	matrika(i,3) = C(i+2*N);
end;

imshow(imgin);
hold("on");
for i=1:N
	center(1) = matrika(i,1);	%dobimo x
	center(2) = matrika(i,2);	%dobimo y
	radij = matrika(i,3);		%dobimo z...radij kroga
	vR = linspace(0.2*pi,NOP,1000);  %vektor 100-ih stevil od 0.2*pi do NOP
	r = ones(1,NOP)*radij;          %vektor NOP "radij" stevil
	[x,y] = pol2cart(vR,r) 			%pretorba v cartesian koordinate
	x = x+center(1);		%pristejemo x
	y = y+center(2);		%pristejemo y
	plot(x,y);
end;
hold("off");
end;

