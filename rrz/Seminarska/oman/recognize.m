function [regions] = recognize(imgin)
slika = imgin;
slikaOriginal = imgin;
%iz RGB v HSV
imgin = rgb2hsv(imgin);
slikaHSV = slika;
%vzamemo Saturation
slika = slika(:,:,2);

%ce je vrednost na sliki manjse od 0.5, tam ni nic, drugace je
slika(slika<0.5) = 0;
slika(slika>=0.5) = 1;

%%Za siv objekt, Value + Saturation - value manjse od 0.4, tam je siv objekt
slikaS = slikaHSV(:,:,3);
slikaS1 = slika .+ (slikaS<0.4);
slikaS1(slikaS1>1) = 1;

slika = slikaS1;
%Znebimo se sumov - manjsih regij
slika = imdilate(slika,4);
slika = imerode(slika,4);

%dobimo ven regije - 8 sosednost
%l==0 - vse regije
%l==1 -prva regija
%l==2 - druga regija...
%num = st.regij
[l, num] = bwlabel(slika,8);

regions = [];
reg = [];
%se sprehodimo cez vse dobljene regije
for i=1:num
	regija = l==i;
	%ce je regija premajhna jo ne obravnavamo
	POVRSINA = dobiPovrsino(regija);
	if(POVRSINA>400)
		zeOblika = 0;		
		%dobimo x-e in y-e
		[y,x] = ind2sub(size(regija), find(regija));
		%shranimo pravokotnik, ki obsega to regijo - poiscemo maksimalne, minimalne x in y
		reg = [reg min(x)];
		reg = [reg min(y)];
		reg = [reg max(x)];
		reg = [reg max(y)];
		%%Barve
		slikaBarve = slikaOriginal;
		%Posamezen kanal barv pomnozimo z regijo, tako dobimo tam kjer je crno crno in tam kjer je regija barvo
		slikaBarve(:,:,1) = slikaBarve(:,:,1) .* regija;
		slikaBarve(:,:,2) = slikaBarve(:,:,2) .* regija;
		slikaBarve(:,:,3) = slikaBarve(:,:,3) .* regija;
		
		%Izracunamo doloceno barvo....sestejemo vse in delimo z vsemi(povprecje)
		red = sum(sum(slikaBarve(:,:,1)))/sum(sum(regija));
		green = sum(sum(slikaBarve(:,:,2)))/sum(sum(regija));
		blue = sum(sum(slikaBarve(:,:,3)))/sum(sum(regija));	
			ze = 0;
			red1 = abs(red -135);
			green1 = green-100;
				%siva
				redS = abs(red-50);
				greenS = abs(green-50);
				blueS = abs(blue-50);
				%Siva je, ce so RGB priblizno enake
				if(redS<20 && greenS <20 && blueS <20)
					reg = [reg 4];
					reg = [reg 3];
					ze = 1;
					zeOblika = 1;
				end;
				%%rumena, ce so priblizno take vrednosti
				if(red1<35 && green1<30 && green1>0 && blue <20)
						reg = [reg 2];
						ze = 1;
				end;
				%Ce ni nobena od prej, je tista barva, ki prevladuje(RGB - ce je R vec od ostalih dveh je rdeca...)
				if(ze == 0 && red>green && red>blue)
					reg = [reg 0];
					ze = 1;
				end;
				if(ze == 0 && green>red && green>blue)
					reg = [reg 3];
					ze = 1;
				end;
				if(ze == 0 && blue>red && blue>green)
					reg = [reg 1];
					ze = 1;
				end;
				
				if(ze == 0)
					reg = [reg -1];
				end;
		okr = 0;
		%Ce je bil siv predmet, ni treba preverjat oblike, ker je siv = "ostalo"
		if(zeOblika==0)
			%%za lopatke, kosarce - velike regije
			%dolocimo "ostalo", drugace izracunamo okroglost za nadaljno iskanje
			if(POVRSINA>4000)
				reg = [reg 3];
				zeOblika = 1;
			else
				okr = dobiOkroglost(regija);
			end;
		end;
		if(zeOblika==0)
			%pravokotna oblika - ima okroglost nekje med 0.3 in 0.6 
			if(okr>= 0.3 && okr <= 0.6)
				reg = [reg 2];
			end;
			%kvadrat ali krog - se jih ne da lociti z okroglostjo
			if(okr>0.6)
				%izracunamo povrsino bounding box-a
				povrsina1 = (abs(max(x)-min(x)))*(abs(max(y)-min(y)));
				%razlika povrsine med bounding box-am in dejansko povrsino lika * 1.29 - ce je krog bi moralo biti priblizno isto
				povrsinaRazlika = abs((POVRSINA*1.29)-povrsina1);
				%Razlika povrsine med bounding box-am in povrsine lika - ce je krog bi morala biti vecja od kvadrata(razen ce je postran)
				povrsinaRazlika1 = abs(povrsina1-POVRSINA);
				%3x if zato, ce je kvadrat slucajno postran
				%prvic prever, ce sta obe lastnosti povrsine kvadrata ujemata s kvadratom
				%drugic prever, ce sta obe lastnosti povrsine kroga ujemata s krogom
				%tretjic prever, ce je slucajno kvadrat obrnjen postran, ima razliko med povrsinami veliko
				%574 - meja pod katero naj bi bil kvadrat
				%35 - meja nad katero naj bi bil kvadrat
				%100 meja pod katero naj bi bil krog ob pogoju z razliko 574 - redki krogi imajo > 35
					if(povrsinaRazlika1<574 && povrsinaRazlika>35 )
						reg = [reg 1]; 
					end;
					if(povrsinaRazlika1>=574 && povrsinaRazlika<100)
						reg = [reg 0];
					end;
					if(povrsinaRazlika1>=574 && povrsinaRazlika>100)
						reg = [reg 1]; 		
					end;
			end;
		end;
		%doda lastosti trenutno pregledanega lika v skupno matriko
		regions = [regions;reg];
		reg = [];
	end;

end;


end;

