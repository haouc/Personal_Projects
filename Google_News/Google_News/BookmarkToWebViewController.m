//
//  BookmarkToWebViewController.m
//  Google_News
//
//  Created by Hao Zhou on 2/15/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "BookmarkToWebViewController.h"

@interface BookmarkToWebViewController ()

@end

@implementation BookmarkToWebViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSArray *dataArr = (NSArray *)[defaults objectForKey:@"favorites"];
    
    dataArr = (NSArray *)[defaults objectForKey:@"favorites"];
    self.myFavorite = [[NSMutableArray alloc] initWithArray:dataArr];
    
//    self.preferredContentSize = CGSizeMake(500.0, 500.0);
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSInteger) numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

- (NSInteger) tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.myFavorite count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    BookMarkTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"bookmarkCell" forIndexPath:indexPath];
    NSMutableDictionary *object = [self.myFavorite objectAtIndex: indexPath.row];
    cell.bookedTitleLabel.text = [object objectForKey:@"title"];
    cell.bookedSnippetLabel.text = [object objectForKey:@"contentSnippet"];
    
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    NSString *dateStr = [object objectForKey:@"publishedDate"];
    [formatter setDateFormat:@"EEE, dd MMM yyyy HH:mm:ss Z"];
    NSDate *date = [[NSDate alloc] init];
    date = [formatter dateFromString:dateStr];
    [formatter setDateFormat: @"MM/dd/yyyy"];
    cell.bookedDateLabel.text = [formatter stringFromDate:date];
    
    return cell;
    
}

- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath{
    return YES;
}

- (void)tableView: (UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        [_myFavorite removeObject:[_myFavorite objectAtIndex:indexPath.row]];
        
        NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
        [defaults setObject:_myFavorite forKey:@"favorites"];
        [defaults synchronize];
        
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    }
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    NSString *url = self.myFavorite[indexPath.row][@"link"];
    [self.delegate bookmark:self sendsURL:[NSURL URLWithString:url]];
    
    UIViewController *gp = self.presentingViewController.presentingViewController;
    [self dismissViewControllerAnimated:YES completion:^{
        [gp dismissViewControllerAnimated:YES completion:nil];
        [self.presentingViewController.presentingViewController dismissViewControllerAnimated:YES completion:nil];
    }];
}


- (IBAction)editButton:(id)sender {
    [self.tableView setEditing:YES animated:YES];
}

- (IBAction)tapButton:(id)sender {
    if(self.tableView.editing == YES){
        [self.tableView setEditing:NO animated:YES];
    }else{
        [self.tableView setEditing:YES animated:YES];
    }
}

@end
