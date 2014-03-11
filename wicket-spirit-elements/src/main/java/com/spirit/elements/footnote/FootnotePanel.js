/**
 * Created by PHT on 25/02/14.
 */
jQuery( document ).ready(function reorderFootnote($, scopeAreaComponentId) {
    var $scopeArea = scopeAreaComponentId ? $('#' + scopeAreaComponentId) : $(document);
    var $footnotesInScopeArea = $scopeArea.find('.footnoteWSE');

    var uniqueTopLeftFootnotesInScopeArea = {};
    $footnotesInScopeArea.each(function() {
        var $this = $(this);
        var footnoteIndex = $this.text();
        var footnoteOffset = $this.offset();

        var topLeftFootnoteForIndex = uniqueTopLeftFootnotesInScopeArea[footnoteIndex];
        var isNewTopLeftFootnote = (typeof topLeftFootnoteForIndex === 'undefined') ||
            (footnoteOffset.top < topLeftFootnoteForIndex.top) ||
            (footnoteOffset.top === topLeftFootnoteForIndex.top && footnoteOffset.left < topLeftFootnoteForIndex.left);

        if (isNewTopLeftFootnote) uniqueTopLeftFootnotesInScopeArea[footnoteIndex] = footnoteOffset;
    });

    var uniqueFootnotesOrderedByOffset = [];
    for (var member in uniqueTopLeftFootnotesInScopeArea) {
        if (uniqueTopLeftFootnotesInScopeArea.hasOwnProperty(member)) {
            uniqueFootnotesOrderedByOffset.push({'index': member, 'offset': uniqueTopLeftFootnotesInScopeArea[member]});
        }
    }
    uniqueFootnotesOrderedByOffset.sort(function (a, b) {
        return a.offset.top !== b.offset.top ? a.offset.top - b.offset.top : a.offset.left - b.offset.top;
    });

    $footnotePaneLines = $(document).find('.footnotePanelWSE li');
    var newPanel = $();
    var arrayLength = uniqueFootnotesOrderedByOffset.length;
    for (var i = 0; i < arrayLength; i++) {

    }

});