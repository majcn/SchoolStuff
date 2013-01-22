%vrne "regions" objektov, ki so na sliki "imgin"
%parameter: imgin (RGB slika dolzina*visina*3)
%return: regions (stObjektov*6)
function [regions] = recognize(imgin)
	HSV = rgb2hsv(imgin);
	%threshold s pomocjo HSV komponent
	S = HSV(:,:,2);
	S(S<0.5) = 0;
	S(S>=0.5) = 1;

	V = HSV(:,:,3);
	temp = S .+ (V<0.4);
	temp(temp>1) = 1;

	gray = temp;
	gray = bwmorph(gray, 'dilate');
	gray = bwmorph(gray, 'dilate');
	gray = bwmorph(gray, 'dilate');
	gray = bwmorph(gray, 'erode');
	gray = bwmorph(gray, 'erode');
	gray = bwmorph(gray, 'erode');
	[l, num] = bwlabel(gray, 8);
	i=1;
	while(i<=num)
		sumObjekt = sum(sum(l==i));
		%iskanje roba in odstranjevanje "majhnih" objektov
		[x, y] = find(l==i);
		if((max(x)>(size(l)(1)-10) && max(y)>(size(l)(2)-10)) || (sumObjekt<100))
			l(l==i) = 0;
			l = l .- (l>i);
			i--;
			num--;
		endif
		i++;
	end

	regions = zeros(num,6);
	for i=1:num
		objekt = (l==i);
		p = my_regionprops(objekt);	%funkcija regionprops v matlabu (nasel na internetu). lahko bi uporabil metodo ki sem jo naredil za domaco nalogo ampak se mi zdi ta bolj optimizirana (central_moments)
		[y, x] = ind2sub(size(objekt), find(objekt));
		regions(i,1) = min(x);
		regions(i,2) = min(y);
		regions(i,3) = max(x);
		regions(i,4) = max(y);
		
		%izlocimo le nas objekt v barvah
		objektImage = imgin;
		objektImage(:,:,1) = objektImage(:,:,1) .* objekt;
		objektImage(:,:,2) = objektImage(:,:,2) .* objekt;
		objektImage(:,:,3) = objektImage(:,:,3) .* objekt;
		
		%povprecna barva objekta
		red = sum(sum(objektImage(:,:,1)))/sum(sum(objekt));
		green = sum(sum(objektImage(:,:,2)))/sum(sum(objekt));
		blue = sum(sum(objektImage(:,:,3)))/sum(sum(objekt));

		maxColor = max([red green blue]);
		
		regions(i,5) = -1;
		%pogoji za nastavitev barve
		if(red<maxColor-50 || green<maxColor-50 || blue<maxColor-50)
			if(red<maxColor-50)
				if(green>blue)
					regions(i,5) = 3;
				else
					regions(i,5) = 1;
				endif
			else
				if(green<maxColor-50 && blue<maxColor-50)
					regions(i,5) = 0;
				else
					regions(i,5) = 2;
				endif
			endif
		else
			regions(i,5) = 4;
		endif
		
		%pogoji za nastavitev oblike
		if(regions(i,5) == 4)								%le cev je siva -> ostalo
			regions(i,6) = 3;
		elseif(p.MajorAxisLength>150)						%vse "velike" stvari so -> ostalo
			regions(i,6) = 3;
		elseif(p.MajorAxisLength<60)						%mozna zamenjava s krogom -> kvadrat
			regions(i,6) = 1;
		elseif(p.MajorAxisLength-p.MinorAxisLength < 10)	%izbrana stevilka za krog
			regions(i,6) = 0;
		elseif(p.MajorAxisLength-p.MinorAxisLength < 50)	%izbrana stevilka za kvadrat
			regions(i,6) = 1;
		elseif(p.MajorAxisLength-p.MinorAxisLength < 100)	%izbrana stevilka za pravokotnik
			regions(i,6) = 2;
		else
			regions(i,6) = 3;
		endif
	end
end