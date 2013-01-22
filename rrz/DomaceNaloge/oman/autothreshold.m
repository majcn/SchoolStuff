function [ imgout ] = autothreshold(imgin)

histogram = histogram(imgin,255); %dobimo histogram
h = [];
max = 0;
max1 = 0;
razdalja = 0;
razdalja1 = 0;

for i=1:255			%poiscemo najvisjo tocko v histogramu in si zapomnimo njeno vrednost in na katerem mestu je
	if((histogram(i)) > max)   
	max = histogram(i);
	razdalja = i;
	end;
end;


for i=1:255			%poiscemo katera je druga najvisja tocka na histogramu in je od prve tocke odaljena vsaj 10 mest
	if((histogram(i))>max1 && (histogram(i))<max && (razdalja-i > 50 || razdalja-i < -50))
		max1 = histogram(i);
		razdalja1 = i;
	end;
end;

if(razdalja<razdalja1)
	prva = razdalja;
	druga = razdalja1;
else
	prva = razdalja1;
	druga = razdalja;
end;
min = max;
for i=prva:druga
	if(histogram(i)<min)
	min = histogram(i);
	end;
end;

imgout = imgin;
imgout(imgout >= min) = 255;
imgout(imgout < min) = 0;






