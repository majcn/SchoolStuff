function [x, y ,d] = disparity(left, right, N)
	[cnr, yR, xR] = harris(right, 2, 10, 3);
	d = [];
	x = [];
	y = [];
	
	%gremo cez vse tocke ki smo jih dobili od "harris"
	for i=1:length(xR)
		%onemogocimo sprehajanje izven slike (matrike)
		if (yR(i)+N<size(right)(1) && xR(i)+N<size(right)(2))
			%dolocimo regijo za ujemanje
			regija = right(yR(i):yR(i)+N, xR(i):xR(i)+N);

			%ujemanje slik ter doloèanje koordinat
			npc = matching(left,regija,'ncc');
			[dY,dX] = ind2sub(size(npc), find(npc==1));

			%dolocanje dispariteta
			d = [d abs(xR(i)-dX)];
			x = [x xR(i)];
			y = [y yR(i)];
		endif
	end
end