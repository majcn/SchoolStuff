%warning: division by zero - se popravt!
%matching: tretji argument.
function [x, y ,d] = disparity(left, right,N)

%iz desne slike dobimo tocke, kjer se nahajajo koti
[cnr ,yl ,xl] = harris(right,2,20,3);
[rightY,rightX] = size(right);

stT = size(xl);

r = round(N/2);

d = [];
x = [];
y = [];
maxI = [];
%sprehodimo se cez vse tocke
    for i=1:stT
        %dobimo regijo iz slike, center je x1 in y1
        yMin = yl(i)-r;
        yMax = yl(i)+r;
        xMin = xl(i)-r;
        xMax = xl(i)+r;
		
        %preverimo, ce je celotna regija na sliki
        if (yMin>0 && yMax<rightY &&xMin>0 && xMax<rightX)
			%dobimo regijo
            regija = right(yMin:yMax,xMin:xMax);
            %normalizirana precna korelacija   
            npc = matching(regija,left,'absdiff');
			
            %odmik
            maxI = [maxI max(abs(npc(:)))];
            [dY, dX] = ind2sub(size(npc),maxI);
            
            dY = dY-r;
            dX = dX-r;
            
        
            %dispariteta
            d = [d abs(xl(i)-dX)];
            x = [x xl(i)];
			y = [y yl(i)];
          
         
        
        end;
    end;
end;

%%%%%%%%%%%%%%%%%%%% matching.m %%%%%%%%%%%%%%%%%%%%%%
function [M] = matching(imgin, tplin, similarity)

[h w] = size(imgin);
[th tw] = size(tplin);
sy = th / 2;
sx = tw / 2;

M = zeros(h-th, w-tw);

imgin = double(imgin);
tplin = double(tplin);

switch(similarity)
case 'absdiff'
	for j = 1:h-th
		for i = 1:w-tw
			region = imgin(j:j+th-1, i:i+tw-1);
			M(j,i) = sum(sum(abs(tplin - region)));
		end
	end
case 'maxdiff'
	for j = 1:h-th
		for i = 1:w-tw
			region = imgin(j:j+th-1, i:i+tw-1);
			M(j,i) = max(max(abs(tplin - region)));
		end
	end
case 'ssd'
	for j = 1:h-th
		for i = 1:w-tw
			region = imgin(j:j+th-1, i:i+tw-1);
			M(j,i) = sum(sum((tplin - region) .^ 2));
		end
	end
	M = sqrt(M);
case 'ncc'
	tplr = sum(sum(tplin .^ 2));
	for j = 1:h-th
		for i = 1:w-tw
			region = imgin(j:j+th-1, i:i+tw-1);
			M(j,i) = sum(sum(tplin .* region)) / sqrt(sum(sum(region .^ 2)) * tplr);
		end
	end
case 'chamfer'
	tplin = tplin ./ 255;
	D = bwdist(double(imgin > 0));
	for j = 1:h-th
		for i = 1:w-tw
			region = D(j:j+th-1, i:i+tw-1);
			M(j,i) = sum(sum(tplin .* region)); 
		end
	end
	M = M ./ sum(sum(tplin));
end


